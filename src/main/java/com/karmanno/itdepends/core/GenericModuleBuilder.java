package com.karmanno.itdepends.core;

public class GenericModuleBuilder {
    private GenericContextModule genericContextModule = new GenericContextModule();

    ContextModule build() {
        return genericContextModule;
    }
}
