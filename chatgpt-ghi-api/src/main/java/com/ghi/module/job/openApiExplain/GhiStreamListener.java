package com.ghi.module.job.openApiExplain;

import com.plexpt.chatgpt.listener.AbstractStreamListener;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * @Auther: LGX
 * @Date: 2023/3/25 14:52
 * @Description: GhiStreamListener
 * @Version 1.0.0
 */
public class GhiStreamListener extends AbstractStreamListener {

    @Override
    public void onMsg(String message) {

    }

    @Override
    public void onError(Throwable throwable, String response) {
        try {
            onError.accept(throwable, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Setter
    @Getter
    protected BiConsumer<Throwable, String> onError = (a, b) -> {};

}
