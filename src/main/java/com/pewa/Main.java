package com.pewa;

import com.pewa.config.ConfigReader;

public class Main {
    public static void main(String[] args) {
        ConfigReader.load();
        UserInterface.search();

    }
}
