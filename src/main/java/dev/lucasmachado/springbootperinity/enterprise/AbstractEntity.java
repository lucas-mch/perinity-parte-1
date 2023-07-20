package dev.lucasmachado.springbootperinity.enterprise;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    @Column(name = "version")
    private int version;
    public AbstractEntity() {
    }
    public AbstractEntity(Long id) {
        this.id = id;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
