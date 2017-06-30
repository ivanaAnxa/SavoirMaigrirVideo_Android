package anxa.com.smvideo.connection;

import android.app.Application;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Hashtable;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.BuildConfig;
import anxa.com.smvideo.common.CommandConstants;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.connection.http.MasterCommand;
import anxa.com.smvideo.connection.http.SavoirMaigrirVideoApiClient;
import anxa.com.smvideo.contracts.BMContract;
import anxa.com.smvideo.contracts.BMQuestionsResponseContract;
import anxa.com.smvideo.contracts.BMResultsResponseContract;
import anxa.com.smvideo.contracts.BMVideoResponseContract;
import anxa.com.smvideo.contracts.CoachingVideosResponseContract;
import anxa.com.smvideo.contracts.RecipeResponseContract;
import anxa.com.smvideo.contracts.UserDataResponseContract;
import anxa.com.smvideo.contracts.VideoDataContract;
import anxa.com.smvideo.contracts.VideoResponseContract;
import anxa.com.smvideo.contracts.WeightGraphResponseContract;
import anxa.com.smvideo.util.AppUtil;

/**
 * Created by angelaanxa on 5/23/2017.
 */

public class ApiCaller {
    private MasterCommand masterCommand;
    private SavoirMaigrirVideoApiClient apiClient;
    private Gson gson;

    {
        gson = new Gson();
        apiClient = new SavoirMaigrirVideoApiClient();
    }

    public ApiCaller() {
        masterCommand = new MasterCommand();
    }

    public void GetFreeDiscover(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.FREE_DISCOVER;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_VIDEOS, command, VideoResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetFreeTestimonials(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.FREE_TESTIMONIALS;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_VIDEOS, command, VideoResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetFreeRecipes(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.FREE_RECIPES;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_RECIPES, command, RecipeResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetBilanMinceurVideo(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.BILANMINCEUR_VIDEO;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_BILANMINCEUR, command, BMVideoResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetBilanMinceurQuestions(AsyncResponse asyncResponse, String Gender) {
        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.BILANMINCEUR_QUESTIONS;

        Hashtable params = new Hashtable();
        params.put("gender", Gender);

        apiClient.GetAsync(asyncResponse, CommandConstants.API_BILANMINCEUR, command, params, BMQuestionsResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void PostBilanMinceurQuestions(AsyncResponse asyncResponse, BMContract contract) {
        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.BILANMINCEUR_RESULTS;

        apiClient.PostAsync(asyncResponse, CommandConstants.API_BILANMINCEUR, command, gson.toJson(contract), BMResultsResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetAccountCoaching(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.ACCOUNT_COACHING + AppUtil.getCurrentWeek();

        apiClient.GetAsync(asyncResponse, CommandConstants.API_VIDEOS, command, CoachingVideosResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetAccountConseils(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.ACCOUNT_CONSEILS;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_VIDEOS, command, VideoResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetAccountExercice(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.ACCOUNT_EXERCICE;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_VIDEOS, command, VideoResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetAccountGraphData(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.ACCOUNT_GRAPH_DATA;
        command.RegId = ApplicationData.getInstance().regId;

        Hashtable params = new Hashtable();
        params.put("applicationId", ApplicationData.getInstance().applicationId);

        apiClient.GetAsync(asyncResponse, CommandConstants.API_TV, command, params, WeightGraphResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void GetAccountUserData(AsyncResponse asyncResponse) {

        MasterCommand command = new MasterCommand();
        command.RegId = ApplicationData.getInstance().regId;

        Hashtable params = new Hashtable();
        params.put("applicationId", ApplicationData.getInstance().applicationId);
        params.put("includeData", true);

        apiClient.GetAsync(asyncResponse, CommandConstants.API_USER, command, params, UserDataResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR);
    }


}
