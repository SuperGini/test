import {RouteResponse} from "./route.response";

export interface RouteResponsePaginated {
    totalRoutes: number;
    routeResponses: RouteResponse []
}
