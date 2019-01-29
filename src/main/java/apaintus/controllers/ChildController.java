package apaintus.controllers;

public interface ChildController<T> {
	public void injectParentController(T controller);
	public void initialize();
}