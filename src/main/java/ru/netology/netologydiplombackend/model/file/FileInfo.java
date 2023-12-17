package ru.netology.netologydiplombackend.model.file;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileInfo {
    @JsonProperty(value = "filename")
    private String name;
    @JsonProperty(value = "size")
    private Long size;

    public FileInfo(String name, Long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
