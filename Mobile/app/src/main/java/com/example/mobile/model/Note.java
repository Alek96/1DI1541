package com.example.mobile.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Note {

    Long id;
    String title;
    String text;
    String createdBy;
}
