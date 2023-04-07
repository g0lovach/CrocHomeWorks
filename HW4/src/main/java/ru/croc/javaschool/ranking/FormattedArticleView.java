package ru.croc.javaschool.ranking;

import java.util.*;

/**
 * Класс описывающий статьи в форматированном для дальнейшей работы представлении
 */
public class FormattedArticleView {

    /**
     * Слова из названия статьи
     */
    private final Set<String> uniqueWordsInName;
    /**
     * Слова в тексте статьи
     */
    private final String[] wordsInText;

    /**
     * Конструктор
     * @param uniqueWordsInName - слова в названии статьи
     * @param wordsInText - слова в тексте статьи
     */
    public FormattedArticleView(Set<String> uniqueWordsInName, String[] wordsInText) {
        this.uniqueWordsInName = uniqueWordsInName;
        this.wordsInText = wordsInText;
    }

    /**
     * Конструктор через запись из условия задачи
     * @param record - запись
     */
    public FormattedArticleView (String record){
        String[] tmp = record.split(";");
        List<String> tmpName = Arrays.asList(tmp[0]
                .toLowerCase(Locale.ROOT)
                .replaceAll("^\\W+|\\W+$", "")
                .split("\\W+")
        );
        uniqueWordsInName = new HashSet<>(tmpName);
        wordsInText = tmp[2].toLowerCase(Locale.ROOT).replaceAll("^\\W+|\\W+$", "").split("\\W+");

    }

    /**
     * Получение слов из названия
     * @return слова из названия
     */
    public Set<String> getUniqueWordsInName() {
        return uniqueWordsInName;
    }

    /**
     * Получение списка слов из текста
     * @return  - список слов в тексте
     */
    public String[] getWordsInText() {
        return wordsInText;
    }

    /**
     * Проверка на эквивалентность
     * @param o - проверяемый объект
     * @return - результат проверки
     */
    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FormattedArticleView formattedArticleView = (FormattedArticleView) o;

        if(wordsInText.length!=((FormattedArticleView) o).wordsInText.length){
            return false;
        }
        if(uniqueWordsInName.size()!=((FormattedArticleView) o).uniqueWordsInName.size()){
            return false;
        }
        String[] tmpUniqueWordsInNameThis = uniqueWordsInName.toArray(new String[0]);
        String[] tmpUniqueWordsInNameO = ((FormattedArticleView) o).getUniqueWordsInName().toArray(new String[0]);
        for (int i = 0; i<uniqueWordsInName.size();i++){
            if (!(tmpUniqueWordsInNameThis[i].equals(tmpUniqueWordsInNameO[i]))){
                return false;
            }
        }
        for (int i = 0; i<wordsInText.length;i++){
            if (!(wordsInText[i].equals(((FormattedArticleView) o).getWordsInText()[i]))){
                return false;
            }
        }

        return true;
    }

    /**
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(uniqueWordsInName);
        result = 31 * result + Arrays.hashCode(wordsInText);
        return result;
    }
}
