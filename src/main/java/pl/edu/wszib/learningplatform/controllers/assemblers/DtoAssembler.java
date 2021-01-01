package pl.edu.wszib.learningplatform.controllers.assemblers;

public abstract class DtoAssembler<TEntity, TDto> {

    public abstract TEntity toEntity(TDto dto);

    public abstract TDto toDto(TEntity entity);

}
