package com.example.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionHandler {

    private boolean canDelete;
    private boolean canAdd;
    private boolean canDisplay;
}
