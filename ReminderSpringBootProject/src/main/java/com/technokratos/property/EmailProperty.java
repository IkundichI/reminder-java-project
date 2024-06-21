package com.technokratos.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class EmailProperty {

    public final String HOST = "smtp.gmail.com";
    public final int PORT = 587;
    public final String username = "artemi.arte.valeev@gmail.com";
    public final String password = "ftpqujczmdwasfdg";
    public final String protocol = "smtp";
    public final String auth = "true";
    public final String starttls = "true";
    public final String debug = "false";
}
