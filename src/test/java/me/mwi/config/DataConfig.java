package me.mwi.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:data.properties")
public interface DataConfig extends Config {

    String phoneNumber();

    String mindBoxUserName();

    String mindBoxPassword();

    @DefaultValue("Михаил")
    String testUserName();

    @DefaultValue("Нестеров")
    String testUserLastName();

}
