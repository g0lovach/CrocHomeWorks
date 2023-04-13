package ru.croc.javaschool.tools;

import java.io.File;

/**
 * Класс с инструментами для работы с директориями системы
 */
public class PathTool {
    /**
     * Метод, создающий при необходимые директории, если они были удалены из папки проекта
     * p.s. В задаче не требовалось, я просто малость побаловался :)
     */
    public static void checkDirExistence(){
        String[] dirs = {"src", "main","resources","datafiles"};
        String path = "";
        File file;
        for(String dir: dirs){
            file = new File(path+dir);

            if (!(file.exists())){
                file.mkdir();
            }
            path+=dir+"/";
        }
    }
}
