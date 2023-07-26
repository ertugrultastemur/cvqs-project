package com.cvqs.mistakelistingservice.service;

import com.cvqs.mistakelistingservice.client.MistakeServiceClient;
import com.cvqs.mistakelistingservice.dto.MistakeDto;
import com.cvqs.mistakelistingservice.dto.MistakeIdDto;
import com.cvqs.mistakelistingservice.dto.UpdateImageDto;
import com.cvqs.mistakelistingservice.dto.VehicleDto;
import com.cvqs.mistakelistingservice.exception.MistakeIOException;
import com.cvqs.mistakelistingservice.exception.MistakeSQLException;
import com.sun.tools.jconsole.JConsoleContext;
import jakarta.validation.constraints.NotNull;
import org.apache.juli.logging.Log;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Service class for Mistake Listings.
 */
@Service
public class MistakeListingService {

    private final MistakeServiceClient mistakeServiceClient;

    /**
     * Constructs a new MistakeListingService with the specified MistakeServiceClient.
     *
     * @param mistakeServiceClient the MistakeServiceClient
     */
    public MistakeListingService(MistakeServiceClient mistakeServiceClient) {
        this.mistakeServiceClient = mistakeServiceClient;
    }

    /**
     * Retrieves all MistakeDto entries.
     *
     * @return the list of MistakeDto entries
     */
    public List<MistakeDto> getAll() {
        return mistakeServiceClient.getAll().getBody();
    }

    /**
     * Retrieves the image by the specified ID.
     *
     * @param id the ID of the MistakeDto
     * @return the image byte array
     * @throws IOException if an I/O error occurs
     */
    public byte[] getImageById(int id) throws IOException {
        MistakeDto mistakeDto = mistakeServiceClient.getById(id).getBody();
        assert mistakeDto != null;
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(mistakeDto.getImage()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    /**
     * Writes the mistake image with the specified ID.
     *
     * @param id the ID of the MistakeDto
     * @throws IOException if an I/O error occurs
     */
    public void writeMistakeImage(int id) throws IOException {
        MistakeDto mistakeDto = mistakeServiceClient.getById(id).getBody();
        assert mistakeDto != null;
        byte[] bytes = mistakeDto.getImage();
        File pointerFile = new File("C:\\campspring\\cvqs-project\\mistake-service\\images\\pointer.png");
        File outputFile = new File("C:\\campspring\\cvqs-project\\mistake-service\\images\\" + mistakeDto.getId().getMistakeName() + ".png");
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(mistakeDto.getImage()));
        BufferedImage pointer = ImageIO.read(pointerFile);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        mistakeDto.getCoordinates().forEach(coordinateDto -> {
            graphics.drawImage(pointer, coordinateDto.getX(), coordinateDto.getY(), null);
        });
        ImageIO.write(image, "png", outputFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        mistakeDto.setImage(imageBytes);
        UpdateImageDto imageDto = new UpdateImageDto(imageBytes, mistakeDto.getId().getMistakeId());
        mistakeServiceClient.update(imageDto);
    }

    /**
     * Finds the MistakeDto entries with sorting based on the specified field.
     *
     * @param field the field to sort by ("name" or "vehicleId")
     * @return the sorted list of MistakeDto entries
     */
    public List<MistakeDto> findMistakesWithSorting(String field) {
        List<MistakeDto> mistakeDtoList = mistakeServiceClient.getAll().getBody();
        assert mistakeDtoList != null;
        switch (field) {
            case "name" -> mistakeDtoList.sort(Comparator.comparing(mistakeDto -> mistakeDto.getId().getMistakeName()));
            default -> mistakeDtoList.sort(Comparator.comparing(MistakeDto::getVehicleId));
        }
        return mistakeDtoList;
    }

    /**
     * Finds the MistakeDto entries with pagination.
     *
     * @param page  the page number
     * @param limit the maximum number of entries per page
     * @return the paginated list of MistakeDto entries
     */
    public Page<MistakeDto> findMistakesWithPagination(int page, int limit) {
        List<MistakeDto> mistakeDtoList = mistakeServiceClient.getAll().getBody();
        assert mistakeDtoList != null;
        int totalElements = mistakeDtoList.size();
        int fromIndex = page * limit;
        int toIndex = Math.min(fromIndex + limit, totalElements);
        List<MistakeDto> pageContent = mistakeDtoList.subList(fromIndex, toIndex);
        PageRequest pageRequest = PageRequest.of(page, limit);
        return new PageImpl<>(pageContent, pageRequest, totalElements);
    }

    /**
     * Finds the MistakeDto entries with name filtering based on the specified field.
     *
     * @param field the field to filter by
     * @return the filtered list of MistakeDto entries
     */
    public List<MistakeDto> findMistakesWithNameFiltering(String field) {
        return mistakeServiceClient.getAll()
                .getBody()
                .stream()
                .filter(mistakeDto -> mistakeDto.getId().getMistakeName().contains(field))
                .collect(Collectors.toList());
    }
}
