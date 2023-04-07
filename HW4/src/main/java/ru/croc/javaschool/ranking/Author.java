package ru.croc.javaschool.ranking;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Класс, описывающий автора статьи
 */
public class Author implements  Comparable<Author>{

    /**
     * имя
     */
    private final String name;

    /**
     * список статей автора в форматированном для дальнейшей работы виде
     */
    private List<FormattedArticleView> formattedArticleViews;

    /**
     * конструктор
     * @param name - имя автора
     */
    public Author(String name) {
        this.name = name;
        this.formattedArticleViews = new LinkedList<>();
    }

    /**
     * Получение имени
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получение статей автора в форматированном виде
     * @return сатьи автора в форматированном виде
     */
    public List<FormattedArticleView> getFormattedArticles() {
        return formattedArticleViews;
    }

    /**
     * Добавить статью в список статей автора
     * @param formattedArticleView - новая статья автора
     */
    public void addArticle(FormattedArticleView formattedArticleView){
        formattedArticleViews.add(formattedArticleView);
    }

    /**
     * Метод проверки на эквивалентность
     * @param o - проверяемый объект
     * @return результат проверки
     */
    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        if(!name.equals(author.name)){
            return false;
        }
        if(formattedArticleViews.size()!=((Author) o).formattedArticleViews.size()){
            return false;
        }
        List<FormattedArticleView> tmpFormattedArticleViews = ((Author) o).formattedArticleViews;
        for(int i = 0; i<formattedArticleViews.size();i++){
            if(!formattedArticleViews.get(i).equals(tmpFormattedArticleViews.get(i))){
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
        return Objects.hash(name, formattedArticleViews);
    }

    /**
     * @return Строковое представление
     */
    @Override
    public String toString() {
        return  name;

    }

    /**
     * Метод сравнения
     * @param o the object to be compared.
     * @return - результат сравнения
     */
    @Override
    public int compareTo(Author o) {
        return this.name.compareTo(o.name);
    }
}
