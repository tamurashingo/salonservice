package com.github.tamurashingo.salonservice.domain.service.salon;

import com.github.tamurashingo.salonservice.domain.model.salon.SalonModel;
import com.github.tamurashingo.salonservice.domain.model.salon.SalonRegisterModel;

import java.util.List;

public interface SalonRegisterService {

    SalonModel createSalon(SalonRegisterModel model) throws SalonRegisterServiceException;

    List<SalonModel> search() throws SalonRegisterServiceException;

}
