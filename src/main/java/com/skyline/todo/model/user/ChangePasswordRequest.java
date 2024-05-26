package com.skyline.todo.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordRequest {
    String currentPassword;
    String newPassword;
}
