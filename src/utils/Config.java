package utils;

public class Config {

    private Config() {

    }

    public static final String REGISTRY_NAME = "Election";
    public static final int HOST = 1888;
    public static final String FILE_NAME = "./files/senadores.csv";
    public static final String FILE_CACHE_NAME = "./files/senadoresCache.txt";
    public static final String FILE_VOTES_CACHE = "./files/votosCache.txt";
    public static final int MAX_TRIES = 5;
    public static final int TRIES_INTERVAL = 6000;
}
