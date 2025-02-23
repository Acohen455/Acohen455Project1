export const store = {
    loggedInUser: {
        userId: 0,
        username: "",
        role: "",
    },

    getLoggedInUserRole: function () {
        return this.loggedInUser.role.toUpperCase();
    },

    resetStore: function(){
        this.loggedInUser = {
            userId: 0,
            username: "",
            role: "",
        }
    }



}