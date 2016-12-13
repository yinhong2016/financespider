package com.sevencolor.entity;

import java.util.List;

/**
 * @Description: 抽象类， 保存历史趋势
 * @param <T>
 * @param <C>
 */
public abstract class TrendEntity<T, C> implements DeepCopy<C> {

  private static final long serialVersionUID = 7051352402018561678L;
  protected final List<T> history;

  public TrendEntity(List<T> history) {
    this.history = history;
  }

  public List<T> getHistory() {
    return history;
  }

}
