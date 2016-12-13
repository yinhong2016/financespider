package com.sevencolor.entity;

import java.io.Serializable;

/**
 * @Description: 深度复制
 * @param <R>
 */
public interface DeepCopy<R> extends Serializable {

  R copy();

}
