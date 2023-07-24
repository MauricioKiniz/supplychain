package com.mksistemas.supplychain.library.processes;

import com.mksistemas.supplychain.library.railway.Result;

public interface Reporter<TFailed, TSuccess> {
	void reportEvent(Result<TFailed, TSuccess> eventToReport);
}
