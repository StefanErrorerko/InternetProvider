package view;

import java.util.List;

public interface View<T> {
    public void show(List<T> entities);
    public void showCreated(T entity);
    public void showDeleted();
    public void showUpdated();
}
