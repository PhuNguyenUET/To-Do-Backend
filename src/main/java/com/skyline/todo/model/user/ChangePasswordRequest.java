package com.skyline.todo.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ChangePasswordRequest {
    String currentPassword;
    String newPassword;
    String confirmationPassword;
}
