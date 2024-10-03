package routes

import (
	"backend/database"
	"backend/middlewares"
	"backend/models"
	"backend/resources/auth"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
)

func addDonationsRoutes(rg *gin.RouterGroup) {
	donation := rg.Group("/donations")

	donation.POST("", auth.GetMiddleware(ClientAuth), middlewares.GetClientID(), func(c *gin.Context) {
		//idClient, _ := c.MustGet("clientID").(uint)
		idClient, exists := c.MustGet("clientID").(uint)
		if !exists {
			c.JSON(http.StatusUnauthorized, gin.H{"error": "Unauthorized:  client ID not found"})
			return
		}

		var donation models.Donation
		if err := c.ShouldBindJSON(&donation); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		donation.ClientID = idClient

		now := time.Now()
		donation.Date = now
		err := database.CreateDonationByClientID(&donation)

		if err != nil {
			c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
			return
		}

		c.JSON(http.StatusCreated, donation)
	})

	donation.GET("", auth.GetMiddleware(AdminAuth), func(c *gin.Context) {
		donations, err := database.GetAllDonations()
		if err != nil {
			c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
			return
		}

		c.JSON(http.StatusOK, donations)
	})
}