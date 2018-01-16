package controllers;

import play.cache.AsyncCacheApi;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RedisController {

    private AsyncCacheApi cache;

    @Inject
    public RedisController(AsyncCacheApi cache) {
        this.cache = cache;
    }

    public CompletionStage<Result> redis(String key) {
        return cache.getOrElseUpdate(key, () -> {
            Thread.sleep(2000);
            return CompletableFuture.completedFuture("Stored value");
        }, 5).thenApply(Results::ok);
    }


    public CompletionStage<Result> redisWithContext(String key) {
        return cache.getOrElseUpdate(key, ()-> {
            Thread.sleep(2000);
            Http.Context context = Http.Context.current();
            return CompletableFuture.completedFuture(context.toString());
        }).thenApply(Results::ok);
    }

}
