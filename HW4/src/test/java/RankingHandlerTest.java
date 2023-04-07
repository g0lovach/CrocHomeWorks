import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.ranking.Author;
import ru.croc.javaschool.ranking.FormattedArticleView;
import ru.croc.javaschool.ranking.RankingHandler;

import java.util.*;

/**
 * Тестовый класс для {@link RankingHandler}
 */
public class RankingHandlerTest {

    /**
     * Тестовый метод для {@link RankingHandler#makeRankingOfAuthors(List)}
     */
    @Test
    public void makeRankingOfAuthors(){
        //Определяем входные данные
        String record1 = "word excel;Suhanov; WoRd,. !?.,-Excel point dir";
        String record2 = "java;Suhanov; python php java java java java java c++ qwe qwe";
        String record3 = "comp comp file file;Sklyar; comp comp comp comp comp comp";
        List<String> records = new LinkedList<>();
        records.add(record1);
        records.add(record2);
        records.add(record3);

        Author author1 = new Author("Sklyar");
        Author author2 = new Author("Suhanov");

        Set<String> wordsInNameOfRecord1 = new LinkedHashSet<>();
        wordsInNameOfRecord1.add("excel");
        wordsInNameOfRecord1.add("word");

        String[] wordsInTextOfRecord1 = new String[]{"word","excel","point","dir"};
        Set<String> wordsInNameOfRecord2 = new LinkedHashSet<>();
        wordsInNameOfRecord2.add("java");


        String[] wordsInTextOfRecord2 = new String[]{"python","php","java","java","java","java","java","c","qwe","qwe"};

        Set<String> wordsInNameOfRecord3 = new LinkedHashSet<>();
        wordsInNameOfRecord3.add("comp");
        wordsInNameOfRecord3.add("file");



        String[] wordsInTextOfRecord3 = new String[]{"comp","comp","comp","comp","comp","comp"};

        FormattedArticleView formattedArticleView1 = new FormattedArticleView(wordsInNameOfRecord1, wordsInTextOfRecord1);
        FormattedArticleView formattedArticleView2 = new FormattedArticleView(wordsInNameOfRecord2, wordsInTextOfRecord2);
        FormattedArticleView formattedArticleView3 = new FormattedArticleView(wordsInNameOfRecord3, wordsInTextOfRecord3);

        author1.addArticle(formattedArticleView3);
        author2.addArticle(formattedArticleView1);
        author2.addArticle(formattedArticleView2);

        //Определяем сверочные данные
        Map<Author,Double> expected = new LinkedHashMap<>();
        expected.put(author1,100.0);
        expected.put(author2,50.0);

        //Результат работы метода
        Map<Author, Double> actual = RankingHandler.makeRankingOfAuthors(records);

        //Проверка на соответствие ожидаемого и полученного результатов
        Assertions.assertEquals(expected, actual);










    }


}
