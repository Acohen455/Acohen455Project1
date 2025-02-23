export const Navbar:React.FC = () => {



    return (
        // https://mdbootstrap.com/docs/standard/components/navbar/
        //bootstrap commands help style
        <nav className="navbar navbar-expand-lg navbar-light bg-body-tertiary fixed-top w-100">

            <div className="container-fluid">

                <a className="navbar-brand me-2" href="https://mdbgo.com/">
                    <img
                        src="https://mdbcdn.b-cdn.net/img/logo/mdb-transaprent-noshadows.webp"
                        height="16"
                        alt="MDB Logo"
                        loading="lazy"
                        style={{marginTop: "-1px;"}}
                    />
                </a>


                <button
                    data-mdb-collapse-init
                    className="navbar-toggler"
                    type="button"
                    data-mdb-target="#navbarButtonsExample"
                    aria-controls="navbarButtonsExample"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <i className="fas fa-bars"></i>
                </button>

                <div className="collapse navbar-collapse" id="navbarButtonsExample">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <a className="nav-link" href="#">Dashboard</a>
                        </li>
                    </ul>


                </div>
            </div>
        </nav>
    )
}