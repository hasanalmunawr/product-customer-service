package hasanalmunawr.dev.backend_spring.web.model;

import hasanalmunawr.dev.backend_spring.base.model.BaseModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    // General Read
    USER_READ("user:read"),
    USER_UPDATE("user:update"),

    // Staff / Operational
    DATA_INPUT("data:input"),
    DATA_UPDATE("data:update"),

    // Manager
    DASHBOARD_VIEW("dashboard:view"),
    REPORT_VIEW("report:view"),

    // Admin
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete")

    // bisa ditambah lagi sesuai fitur
    ;

    private final String permission;

}
