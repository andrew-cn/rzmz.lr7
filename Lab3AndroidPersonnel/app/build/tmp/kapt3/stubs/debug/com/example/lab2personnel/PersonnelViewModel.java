package com.example.lab2personnel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\rJ\u0006\u0010\u0012\u001a\u00020\rJ\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u0017H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006%"}, d2 = {"Lcom/example/lab2personnel/PersonnelViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/example/lab2personnel/PersonnelRepository;", "(Lcom/example/lab2personnel/PersonnelRepository;)V", "_ui", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/lab2personnel/UiState;", "ui", "Lkotlinx/coroutines/flow/StateFlow;", "getUi", "()Lkotlinx/coroutines/flow/StateFlow;", "addPerson", "", "askDelete", "index", "", "cancelDelete", "clearAll", "confirmDelete", "onSnackbarShown", "setAge", "value", "", "setHireDate", "setHoursWorked", "setName", "setPosition", "setProjectsCount", "setSalary", "setShift", "setSpecialization", "setType", "type", "Lcom/example/lab2personnel/PersonaType;", "showError", "message", "app_debug"})
public final class PersonnelViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.lab2personnel.PersonnelRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.lab2personnel.UiState> _ui = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.lab2personnel.UiState> ui = null;
    
    public PersonnelViewModel(@org.jetbrains.annotations.NotNull()
    com.example.lab2personnel.PersonnelRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.lab2personnel.UiState> getUi() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.NotNull()
    com.example.lab2personnel.PersonaType type) {
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setAge(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setHireDate(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setShift(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setHoursWorked(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setSalary(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setSpecialization(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setProjectsCount(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void addPerson() {
    }
    
    public final void askDelete(int index) {
    }
    
    public final void confirmDelete() {
    }
    
    public final void cancelDelete() {
    }
    
    public final void clearAll() {
    }
    
    public final void onSnackbarShown() {
    }
    
    private final void showError(java.lang.String message) {
    }
}