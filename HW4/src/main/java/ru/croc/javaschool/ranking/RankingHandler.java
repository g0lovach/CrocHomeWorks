package ru.croc.javaschool.ranking;

import ru.croc.javaschool.tools.MapUtil;

import java.util.*;

/**
 * Класс, предназначенный для работы с записями и авторами
 */
public class RankingHandler {

    /**
     * Получение автора из записи
     * @param authors - список авторов
     * @param record - запись
     * @return - автор
     */
    private static Author getAuthorFromRecord(List<Author> authors, String record){

        String[] tmp = record.split(";");
        for(Author author: authors){
            if (tmp[1].equals(author.getName())){
                return author;
            }
        }
        return new Author(tmp[1]);
    }


    /**
     * Получение списка авторов из списка записей
     * @param records - записи
     * @return - список авторов
     */
    private static List<Author> createAuthorsListFromRecords(List<String> records){
        List<Author> authors = new LinkedList<>();
        for (String record: records){
            Author tmp = getAuthorFromRecord(authors, record);
            tmp.addArticle(new FormattedArticleView(record));
            if(!authors.contains(tmp)){
                authors.add(tmp);
            }

        }
        return authors;

    }

    /**
     * Получение статистики встречаемости слов из названия в тексте статьи для одной статьи
     * @param formattedArticleView - статья в форматированном виде
     * @return - встречаемость в процентах
     */
    private static double getStatisticForArticle(FormattedArticleView formattedArticleView){
        double res = 0;
        String[] tmpTextOfArticle = formattedArticleView.getWordsInText();
        Set<String> tmpNameOfArticle = formattedArticleView.getUniqueWordsInName();
        Map<String,Integer> countOfWordsFromNameInText = new HashMap<>();
        for (String wordInNameOfArticle: tmpNameOfArticle){
            for(String wordInTextOfArticle: tmpTextOfArticle){
                if(wordInTextOfArticle.equals(wordInNameOfArticle)) {
                    countOfWordsFromNameInText.put(
                            wordInTextOfArticle,
                            countOfWordsFromNameInText.getOrDefault(wordInTextOfArticle, 0) + 1
                    );
                }
            }
        }
        return  countOfWordsFromNameInText.values().stream()
                .mapToDouble(i -> i)
                .sum()*100/ tmpTextOfArticle.length;
    }

    /**
     * Ранжирование авторов по встречаемости слов из названия статьи в тексте статьи. Ранжирование
     * осуществляется по убыванию процента встречаемости, а затем по имени в алфавитном порядке
     * @param records - записи
     * @return - ранжированный список
     */
    public static Map<Author, Double> makeRankingOfAuthors(List<String> records){
        Map<Author, Double> res = new LinkedHashMap<>();
        List<Author> authors = createAuthorsListFromRecords(records);
        for(Author author: authors){
            double percent = 0;
            List<FormattedArticleView> formattedArticleViews = author.getFormattedArticles();
            for(FormattedArticleView formattedArticleView : formattedArticleViews){
                percent+= getStatisticForArticle(formattedArticleView);
            }
            percent/= formattedArticleViews.size();
            res.put(author,percent);
        }

      return MapUtil.sortByValueKey(res);
    }

}
