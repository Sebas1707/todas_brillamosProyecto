/*
 * Backend-models: Código que contiene el modelo de Product y sus atributos
 * @author: Carlos Zamudio
 * @co-author: Jennyfer Jasso
 * @co-author: Min Che Kim
 */

package models

/*
 * Estructura de la tabla Product
 */
type Product struct {
	ID uint `json:"id" gorm:"primarykey" validate:"omitempty,eq=0"`

	// Image
	Hash string `json:"hash"`
	Type string `json:"type"`

	// General
	Model       string  `json:"model"`
	Name        string  `json:"name"`
	Description string  `json:"description"`
	Price       float64 `json:"price"`
	Stock       int     `json:"stock"`

	// Specific
	Size        string `json:"size"`
	Color       string `json:"color"`
	Maintenance string `json:"maintenance"`
	Material    string `json:"material"`
	Absorbency  string `json:"absorbency"`
	MatFeature  string `json:"material_feature"`

	// Relationships
	CategoryID uint `json:"category_id"`
}

/*
 * Estructura de la metadata de Product
 */
type ProductMetadata struct {
	// General
	Model       string  `json:"model" validate:"required,min=1"`
	Name        string  `json:"name" validate:"required,min=1"`
	Description string  `json:"description" validate:"required"`
	Price       float64 `json:"price" validate:"required,min=0"`
	Stock       int     `json:"stock" validate:"required,min=0"`

	// Specific
	Size        string `json:"size" validate:"required"`
	Color       string `json:"color" validate:"required"`
	Maintenance string `json:"maintenance" validate:"required"`
	Material    string `json:"material" validate:"required"`
	Absorbency  string `json:"absorbency" validate:"required"`
	MatFeature  string `json:"material_feature" validate:"required"`

	// Relationships
	CategoryID uint `json:"category_id" validate:"required,min=0"`
}

func (p *Product) SetID(id uint) {
	p.ID = id
}

func (p *Product) GetHash() *string {
	return &p.Hash
}

func (p *Product) GetType() *string {
	return &p.Type
}

func (p *Product) SetMetadata(metadata *ProductMetadata) {
	if metadata == nil {
		return
	}

	p.Model = metadata.Model
	p.Name = metadata.Name
	p.Description = metadata.Description
	p.Price = metadata.Price
	p.Stock = metadata.Stock
	p.Size = metadata.Size
	p.Color = metadata.Color
	p.Maintenance = metadata.Maintenance
	p.Material = metadata.Material
	p.Absorbency = metadata.Absorbency
	p.MatFeature = metadata.MatFeature
	p.CategoryID = metadata.CategoryID
}
