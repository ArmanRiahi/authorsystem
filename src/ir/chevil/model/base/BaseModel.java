package ir.chevil.model.base;

public class BaseModel<ID> {
    private ID id;

    public void setId(ID id) {this.id = id;}
    public ID getId() {return id;}
}
