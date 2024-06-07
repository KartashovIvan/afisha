package org.javaacademy.afisha.servcie;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.PlaceDto;
import org.javaacademy.afisha.entity.Place;
import org.javaacademy.afisha.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PlaceService {
    private PlaceRepository placeRepository;

    public void addNewPlace(PlaceDto place) {
        placeRepository.addNewPlace(convertDtoToPlace(place));
    }

    public List<PlaceDto> takeAllPlace() {
        return placeRepository.getAllPlace().stream()
                .map(place -> new PlaceDto(place.getName(), place.getAddress(), place.getCity()))
                .toList();
    }

    public Place takePlace(PlaceDto place) {
        return placeRepository.checkExistsPlace(convertDtoToPlace(place)).stream()
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Not fount place"));
    }

    public PlaceDto getPlaceById(Integer id) {
        Place place = placeRepository.getPlaceById(id);
        return convertPlaceToDto(place);
    }

    private Place convertDtoToPlace(PlaceDto placeDto) {
        return new Place(null, placeDto.getName(), placeDto.getAddress(), placeDto.getCity());
    }

    private PlaceDto convertPlaceToDto(Place place) {
        return new PlaceDto(place.getName(), place.getAddress(), place.getCity());
    }
}
