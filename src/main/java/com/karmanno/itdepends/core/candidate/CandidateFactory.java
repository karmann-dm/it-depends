package com.karmanno.itdepends.core.candidate;

import com.karmanno.itdepends.core.Context;

@FunctionalInterface
public interface CandidateFactory<T> {
    T create(Context context, Object... args);
}
