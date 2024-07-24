package com.chan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DetInfoDTO {
    private int id;
    private List<Integer> position;
    private List<String> attribute;

}
