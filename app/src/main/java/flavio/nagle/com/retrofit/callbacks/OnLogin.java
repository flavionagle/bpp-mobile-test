package flavio.nagle.com.retrofit.callbacks;

import flavio.nagle.com.model.LoginResponse;

public interface OnLogin {
    void onSuccess(LoginResponse o);

    void onError(LoginResponse o);
}
