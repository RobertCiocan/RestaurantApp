package com.gastrosfera.ospatar.service;

import com.gastrosfera.shared.v1.ospatar.dto.OspatarDTO;

public interface OspatarService {

    OspatarDTO createOspatar(OspatarDTO ospatarDTO);
    OspatarDTO getOspatar(String uuid);

}
