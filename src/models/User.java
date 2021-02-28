package models;

import utils.HashMD5;

public class User implements Comparable<User> {
    private String name;
    private String hash;

    public User(String name) {
        this.name = name;
        this.hash = new HashMD5().getMD5(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public int compareTo(User o) {
        return this.hash.compareTo(o.getHash());
    }
}
