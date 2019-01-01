package com.translate.data;

import com.translate.model.Group_class;
import com.translate.model.Part;
import com.translate.model.Vocabulary;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReadFile {
    private List<Group_class> group_classList;
    private List<Part> partList;
    private List<Vocabulary> vocabularyList;
    private BufferedReader bufferedReaderCroup, bufferedReaderPart, bufferedReaderVocab;

    public ReadFile(BufferedReader bufferedReaderCroup, BufferedReader bufferedReaderPart, BufferedReader bufferedReaderVocab) throws IOException {
        this.bufferedReaderCroup = bufferedReaderCroup;
        this.bufferedReaderPart = bufferedReaderPart;
        this.bufferedReaderVocab = bufferedReaderVocab;
        readFile();
    }

    public List<Group_class> getGroup_classList() {
        return group_classList;
    }

    public List<Part> getPartList() {
        return partList;
    }

    public List<Vocabulary> getVocabularyList() {
        return vocabularyList;
    }

    private void readFile() throws IOException {

        group_classList = new ArrayList<>();
        String sc;
        while ((sc = bufferedReaderCroup.readLine()) != null) {
            Group_class group_class = (Group_class) part(sc, 1);
            group_classList.add(group_class);
        }
        bufferedReaderCroup.close();

        partList = new ArrayList<>();
        String sp;
        while ((sp = bufferedReaderPart.readLine()) != null) {
            Part part = (Part) part(sp, 2);
            partList.add(part);
        }
        bufferedReaderPart.close();

        vocabularyList = new ArrayList<>();
        String sv;
        while ((sv = bufferedReaderVocab.readLine()) != null) {

            Vocabulary vocabulary = (Vocabulary) part(sv, 3);
            vocabularyList.add(vocabulary);
        }
        bufferedReaderVocab.close();
    }

    private Object part(String s, int i) {
        String[] strings = s.split(Pattern.quote("="));
        if (i == 1) {
            Group_class group_class = new Group_class(Integer.parseInt(strings[0]), strings[1], strings[2]);
            return group_class;
        }
        if (i == 2) {
            Part part = new Part(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]));
            return part;
        } else {
            return new Vocabulary(Integer.parseInt(strings[0].trim()), strings[1], strings[2],
                    strings[3], strings[4], strings[5], Integer.parseInt(strings[6]));
        }
    }

    public static void main(String[] args) {

    }
}
