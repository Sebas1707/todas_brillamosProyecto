package database

import "backend/models"

// GetPostByID obtiene un post por su ID.
// Devuelve un puntero a models.Post y un error en caso de que ocurra.
func GetPostByID(id uint) (*models.Post, error) {
	post := &models.Post{}
	if err := db.First(post, id).Error; err != nil {
		return nil, err
	}

	return post, nil
}

// GetPosts obtiene todos los posts de la base de datos.
// Devuelve una lista de posts y un error en caso de que ocurra.
func GetPosts() ([]models.Post, error) {
	var posts []models.Post
	if err := db.Find(&posts).Error; err != nil {
		return nil, err
	}

	return posts, nil
}

// CreatePost crea un nuevo post en la base de datos.
// Devuelve el ID del post creado y un error en caso de que ocurra.
func CreatePost(post *models.Post) (uint, error) {
	if err := db.Create(post).Error; err != nil {
		return 0, err
	}

	return post.ID, nil
}

// DeletePost elimina un post por su ID.
// Devuelve el post eliminado y un error en caso de que ocurra.
func DeletePost(id uint) (*models.Post, error) {
	post, err := GetPostByID(id)
	if err != nil {
		return nil, err
	}

	if err := db.Delete(post).Error; err != nil {
		return nil, err
	}

	return post, nil
}