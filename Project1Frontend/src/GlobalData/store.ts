export const store = {
    loggedInUser: {
        userId: 0,
        username: "",
        role: "",
    },

    getLoggedInUser: function () {
        return this.loggedInUser.role.toUpperCase();
    }
}