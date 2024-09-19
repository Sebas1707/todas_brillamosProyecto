package database

import (
	"backend/models"
	"errors"

	"gorm.io/gorm"
)

func GetUserByClientID(id int) (*models.User, error) {
	var user models.User
	err := GetDatabase().
		Joins("JOIN clients ON clients.user_id = users.id").
		Where("clients.id = ?", id).
		First(&user).Error
	return &user, err
}

func GetUserByClientEmail(email string) (*models.User, error) {
	var user models.User
	db := GetDatabase()

	err := db.Joins("JOIN clients ON clients.user_id = users.id").
		Where("users.email = ?", email).
		First(&user).Error

	if err != nil {
		return nil, err
	}

	return &user, nil
}

func CreateClient(clientUser *models.ClientUser) (uint, error) {
	db := GetDatabase()
	tx := db.Begin()

	var existingClient models.Client
	err := tx.Joins("JOIN users ON users.id = clients.user_id").
		Where("users.email = ?", clientUser.Email).
		First(&existingClient).Error

	if err == nil {
		tx.Rollback()
		return 0, errors.New("client already exists")
	} else if err != gorm.ErrRecordNotFound {
		tx.Rollback()
		return 0, err
	}

	var user = models.User{
		Email:    clientUser.Email,
		Password: clientUser.Password,
	}
	if err := tx.Create(&user).Error; err != nil {
		tx.Rollback()
		return 0, err
	}

	client := models.Client{
		FirstName: clientUser.FirstName,
		LastName:  clientUser.LastName,
		UserID:    user.ID,
	}
	if err := tx.Create(&client).Error; err != nil {
		tx.Rollback()
		return 0, err
	}

	if err := tx.Commit().Error; err != nil {
		return 0, err
	}

	return client.ID, nil
}