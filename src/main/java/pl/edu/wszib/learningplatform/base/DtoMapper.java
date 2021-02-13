package pl.edu.wszib.learningplatform.base;

public abstract class DtoMapper<TEntity, TDto> {

    public abstract TEntity toEntity(TDto dto);

    public abstract TDto toDto(TEntity entity);

}
