package com.translate.model;

public class Vocabulary {
    private int Id;
    private String word;
    private String mean;
    private String spelling;
    private String type;
    private String audioFile;
    private int IdP;

    public Vocabulary(int id, String word, String mean, String spelling, String type, String audioFile, int idP) {
        Id = id;
        this.word = word;
        this.mean = mean;
        this.spelling = spelling;
        this.type = type;
        this.audioFile = audioFile;
        IdP = idP;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }

    public int getIdP() {
        return IdP;
    }

    public void setIdP(int idP) {
        IdP = idP;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "Id=" + Id +
                ", word='" + word + '\'' +
                ", mean='" + mean + '\'' +
                ", spelling='" + spelling + '\'' +
                ", type='" + type + '\'' +
                ", audioFile='" + audioFile + '\'' +
                ", IdP=" + IdP +
                '}';
    }
}
