package com.karmanno.itdepends.core.examples.annotation_policy;

import com.karmanno.itdepends.core.component.InstantiationPolicy;
import com.karmanno.itdepends.core.scan.ContextScoped;

@ContextScoped(instantiationPolicy = InstantiationPolicy.LAZY)
public class PolicyClass {
}
