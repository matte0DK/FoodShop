package be.intecbrussel.foodshop.config;

import java.io.File;

public enum FilePath {
    CUSTOMER_FILEPATH("resources/CustomerFile.txt"),
    STOCK_FILEPATH("resources/StockFile.txt");

    private File file;

    private FilePath(String path) {
        file = new File(path);
    }

    public File getFile() {
        return this.file;
    }
}
