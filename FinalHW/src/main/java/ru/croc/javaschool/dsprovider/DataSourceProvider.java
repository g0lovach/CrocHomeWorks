package ru.croc.javaschool.dsprovider;

import javax.sql.DataSource;

/**
 * Интерфейс провайдера источника данных.
 */
public interface DataSourceProvider {

    /**
     * Метод получения источника данных.
     *
     * @return источник данных
     */
    DataSource getDataSource();
}