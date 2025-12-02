package com.example.lab2personnel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B!\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u0082\u0001\u0003\u000f\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/example/lab2personnel/Persona;", "", "name", "", "age", "", "hireDate", "(Ljava/lang/String;ILjava/lang/String;)V", "getAge", "()I", "getHireDate", "()Ljava/lang/String;", "getName", "doWork", "printInfo", "Lcom/example/lab2personnel/Inzhener;", "Lcom/example/lab2personnel/Robochyi;", "Lcom/example/lab2personnel/Sluzhbovets;", "app_debug"})
public abstract class Persona {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    private final int age = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String hireDate = null;
    
    private Persona(java.lang.String name, int age, java.lang.String hireDate) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    public int getAge() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getHireDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String printInfo();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String doWork();
}