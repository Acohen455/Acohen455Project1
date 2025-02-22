// global.d.ts
declare module '*.module.css' {
    const classes: { [key: string]: string };
    export default classes;
}

declare module '*.css' {
    const styles: { [key: string]: string };
    export default styles;
}