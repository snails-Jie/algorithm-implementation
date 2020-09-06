package zhangjie.mapper.mybatis.model;

import javax.persistence.*;

@Table(name = "t_film_statistic")
public class TFilmStatistic {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "view_count")
    private Long viewCount;

    private Integer flag;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return film_id
     */
    public Long getFilmId() {
        return filmId;
    }

    /**
     * @param filmId
     */
    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    /**
     * @return view_count
     */
    public Long getViewCount() {
        return viewCount;
    }

    /**
     * @param viewCount
     */
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * @return flag
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * @param flag
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}