package anxa.com.smvideo.connection;

import android.os.AsyncTask;

import com.google.gson.Gson;

import anxa.com.smvideo.common.CommandConstants;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.connection.http.MasterCommand;
import anxa.com.smvideo.connection.http.SavoirMaigrirVideoApiClient;
import anxa.com.smvideo.contracts.RecipeResponseContract;
import anxa.com.smvideo.contracts.VideoDataContract;
import anxa.com.smvideo.contracts.VideoResponseContract;

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
    public ApiCaller()
    {
        masterCommand = new MasterCommand();
    }
    public  void GetFreeDiscover(AsyncResponse asyncResponse)
    {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.FREE_DISCOVER;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_VIDEOS, command, VideoResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR) ;
    }
    public  void GetFreeTestimonials(AsyncResponse asyncResponse)
    {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.FREE_TESTIMONIALS;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_VIDEOS, command, VideoResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR) ;
    }
    public  void GetFreeRecipes(AsyncResponse asyncResponse)
    {

        MasterCommand command = new MasterCommand();
        command.Command = CommandConstants.FREE_RECIPES;

        apiClient.GetAsync(asyncResponse, CommandConstants.API_RECIPES, command, RecipeResponseContract.class, AsyncTask.THREAD_POOL_EXECUTOR) ;
    }
}
