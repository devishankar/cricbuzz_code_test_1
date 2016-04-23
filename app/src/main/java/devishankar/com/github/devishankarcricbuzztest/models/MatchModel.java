package devishankar.com.github.devishankarcricbuzztest.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @author Devishankar
 */
public class MatchModel implements Serializable, Comparable {
    private int id;
    private String matchDesc;
    private int seriesId;
    private String seriesDesc;
    private String category;
    private String status;
    private int startDate;
    private String teamAName;
    private String teamAShortName;
    private String teamBName;
    private String teamBShortName;
    private int teamAImageId;
    private int teamBImageId;
    private boolean isPrevDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatchDesc() {
        return matchDesc;
    }

    public void setMatchDesc(String matchDesc) {
        this.matchDesc = matchDesc;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesDesc() {
        return seriesDesc;
    }

    public void setSeriesDesc(String seriesDesc) {
        this.seriesDesc = seriesDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamAShortName() {
        return teamAShortName;
    }

    public void setTeamAShortName(String teamAShortName) {
        this.teamAShortName = teamAShortName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public String getTeamBShortName() {
        return teamBShortName;
    }

    public void setTeamBShortName(String teamBShortName) {
        this.teamBShortName = teamBShortName;
    }

    public int getTeamAImageId() {
        return teamAImageId;
    }

    public void setTeamAImageId(int teamAImageId) {
        this.teamAImageId = teamAImageId;
    }

    public int getTeamBImageId() {
        return teamBImageId;
    }

    public void setTeamBImageId(int teamBImageId) {
        this.teamBImageId = teamBImageId;
    }

    public void setIsPrevDay(boolean isPrevDay) {
        this.isPrevDay = isPrevDay;
    }

    public boolean isPrevDay() {
        return isPrevDay;
    }

    public void setPrevDay(boolean isPrevDay) {
        this.isPrevDay = isPrevDay;
    }

    @Override
    public int compareTo(@NonNull Object another) {
        int date = ((MatchModel) (another)).getStartDate();
        return this.id - id;
    }

    @Override
    public String toString() {
        return "MatchModel{" +
                "id=" + id +
                ", matchDesc='" + matchDesc + '\'' +
                ", seriesId=" + seriesId +
                ", seriesDesc='" + seriesDesc + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", teamAName='" + teamAName + '\'' +
                ", teamAShortName='" + teamAShortName + '\'' +
                ", teamBName='" + teamBName + '\'' +
                ", teamBShortName='" + teamBShortName + '\'' +
                ", teamAImageId=" + teamAImageId +
                ", teamBImageId=" + teamBImageId +
                ", isPrevDay=" + isPrevDay +
                '}';
    }
}
